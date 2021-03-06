/*
 *     Copyright (C) 2020 STG_Allen
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.anvilpowered.catalyst.common.module;

import com.google.common.reflect.TypeToken;
import com.google.inject.AbstractModule;
import org.anvilpowered.anvil.api.Anvil;
import org.anvilpowered.anvil.api.data.config.ConfigurationService;
import org.anvilpowered.anvil.api.data.registry.Registry;
import org.anvilpowered.anvil.api.misc.BindingExtensions;
import org.anvilpowered.anvil.api.plugin.BasicPluginInfo;
import org.anvilpowered.anvil.api.plugin.PluginInfo;
import org.anvilpowered.catalyst.api.member.MemberManager;
import org.anvilpowered.catalyst.api.plugin.PluginMessages;
import org.anvilpowered.catalyst.api.plugin.StaffListService;
import org.anvilpowered.catalyst.api.service.ChatFilter;
import org.anvilpowered.catalyst.api.service.ChatService;
import org.anvilpowered.catalyst.api.service.PrivateMessageService;
import org.anvilpowered.catalyst.common.chat.CommonChatFilter;
import org.anvilpowered.catalyst.common.chat.CommonChatService;
import org.anvilpowered.catalyst.common.chat.CommonPrivateMessageService;
import org.anvilpowered.catalyst.common.data.config.CatalystConfigurationService;
import org.anvilpowered.catalyst.common.data.registry.CatalystRegistry;
import org.anvilpowered.catalyst.common.member.CommonMemberManager;
import org.anvilpowered.catalyst.common.plugin.CatalystPluginInfo;
import org.anvilpowered.catalyst.common.plugin.CatalystPluginMessages;
import org.anvilpowered.catalyst.common.plugin.CommonStaffListService;

@SuppressWarnings({"UnstableApiUsage"})
public class CommonModule<
    TUser,
    TPlayer extends TCommandSource,
    TString,
    TCommandSource>
    extends AbstractModule {

    @Override
    protected void configure() {

        BindingExtensions be = Anvil.getBindingExtensions(binder());

        be.bind(new TypeToken<PluginInfo<TString>>(getClass()) {
        }, new TypeToken<CatalystPluginInfo<TString, TCommandSource>>(getClass()) {
        });

        be.bind(new TypeToken<BasicPluginInfo>(getClass()) {
        }, new TypeToken<CatalystPluginInfo<TString, TCommandSource>>(getClass()) {
        });

        be.bind(
            new TypeToken<PluginMessages<TString>>(getClass()) {
            },
            new TypeToken<CatalystPluginMessages<TString, TCommandSource>>(getClass()) {
            }
        );

        be.bind(
            new TypeToken<MemberManager<TString>>(getClass()) {
            },
            new TypeToken<CommonMemberManager<TUser, TPlayer, TString, TCommandSource>>(getClass()) {
            }
        );

        be.bind(
            new TypeToken<ChatService<TString, TPlayer>>(getClass()) {
            },
            new TypeToken<CommonChatService<TPlayer, TString, TCommandSource>>(getClass()) {
            }
        );

        be.bind(
            new TypeToken<PrivateMessageService<TString>>(getClass()) {
            },
            new TypeToken<CommonPrivateMessageService<TPlayer, TString, TCommandSource>>(getClass()) {
            }
        );

        be.bind(
            new TypeToken<StaffListService<TString>>(getClass()) {
            },
            new TypeToken<CommonStaffListService<TPlayer, TString, TCommandSource>>(getClass()) {

            }
        );

        bind(ChatFilter.class).to(CommonChatFilter.class);
        bind(ConfigurationService.class).to(CatalystConfigurationService.class);
        bind(Registry.class).to(CatalystRegistry.class);
    }
}

/*******************************************************************************
 * Copyright 2017 jamietech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ch.jamiete.hilda.admin.commands;

import java.util.Arrays;
import java.util.Optional;
import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.Util;
import ch.jamiete.hilda.commands.ChannelSeniorCommand;
import ch.jamiete.hilda.commands.ChannelSubCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class AdminBroadcastCommand extends ChannelSubCommand {

    public AdminBroadcastCommand(final Hilda hilda, final ChannelSeniorCommand senior) {
        super(hilda, senior);

        this.setName("broadcast");
        this.setAliases(Arrays.asList(new String[] { "announce" }));
        this.setDescription("Broadcasts an announcement to all servers.");
    }

    @Override
    public void execute(final Message message, final String[] arguments, final String label) {
        for (final Guild guild : this.hilda.getBot().getGuilds()) {
            final TextChannel channel = guild.getPublicChannel();
            final Member self = guild.getMember(this.hilda.getBot().getSelfUser());

            if (self.hasPermission(channel, Permission.MESSAGE_WRITE)) {
                channel.sendMessage("**NOTICE FROM ADMINISTRATOR:** " + Util.combineSplit(1, arguments, " ")).queue();
                continue;
            }

            final Optional<TextChannel> optional = guild.getTextChannels().stream().filter(chan -> self.hasPermission(chan, Permission.MESSAGE_WRITE)).findFirst();
            if (optional.isPresent()) {
                optional.get().sendMessage("**NOTICE FROM ADMINISTRATOR:** " + Util.combineSplit(0, arguments, " ")).queue();
                continue;
            }

            Hilda.getLogger().warning("Failed to find channel to send message to on " + guild.getName() + " " + guild.getId());
        }

        this.reply(message, "Sent notice!");
    }

}
